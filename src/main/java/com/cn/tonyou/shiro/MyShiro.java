package com.cn.tonyou.shiro;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import com.cn.tonyou.pojo.SysAuthInfo;
import com.cn.tonyou.pojo.SysRoleInfo;
import com.cn.tonyou.pojo.SysUserInfo;
import com.cn.tonyou.service.ISysUserInfoService;
public class MyShiro extends AuthorizingRealm{
	@Autowired
	private ISysUserInfoService userInfoService;
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取到当前的user
		ShiroUser userInfo = (ShiroUser)principals.getPrimaryPrincipal();
		//到数据库查询是否有该对象
		SysUserInfo sysUserInfo = userInfoService.getUserByName(userInfo.loginName);
		//角色名称集合
		Set<String> roleList = new HashSet<String>(); 
		//存储权限名称集合
		Set<String> permissionList = new HashSet<String>(); 
		if (sysUserInfo != null) {
			//实体类User中包含有用户角色的实体类信息
			Set<SysRoleInfo> sysroleInfos= sysUserInfo.getSysRoleInfos();
			//判断是否为空
			if (!sysroleInfos.isEmpty()) {
				//便利获取获取当前登录用用户角色
				Iterator<SysRoleInfo> iterator = sysroleInfos.iterator();
				while (iterator.hasNext()) {
					SysRoleInfo roleInfo = iterator.next();
					roleList.add(roleInfo.getSysRoleName());
					 //实体类Role中包含有角色权限的实体类信息
					Set<SysAuthInfo> sysAuthInfos = roleInfo.getSysAuthInfos();
					if (!sysAuthInfos.isEmpty()) {
						for (SysAuthInfo sysAuthInfo:sysAuthInfos) {
							permissionList.add(sysAuthInfo.getSysAuthName());
						}

					}
					
				}
			}
			//权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			authorizationInfo .addRoles(roleList);
			authorizationInfo .addStringPermissions(permissionList);
			return authorizationInfo;
		}
		return null;
	}
	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		//UsernamePasswordToken对象用来存放提交的登录信息
		UsernamePasswordToken user = (UsernamePasswordToken) authenticationToken;
		//到数据库查询是否有该对象
		SysUserInfo sysUserInfo = userInfoService.getUserByName(user.getUsername());
		if (sysUserInfo != null) {
			String schoolId = null;
			if(sysUserInfo.getSchool()!=null){
				schoolId = sysUserInfo.getSchool().getSchoolId();
			}
			SimpleAuthenticationInfo info = 
					new SimpleAuthenticationInfo(
							new ShiroUser(sysUserInfo.getSysUserId(), sysUserInfo.getSysUserName(),schoolId),
							sysUserInfo.getSysUserPassWord(), 
							getName());
			
			return info;
		}
		return null;
	}  
	/**
     * 清除用户认证信息
     * @param principal
     */
    protected void clearCachedAuthenticationInfo(String principal) {
        // TODO Auto-generated method stub
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principal, getName());
        clearCachedAuthenticationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
  
	/**
	 * 设定PassWord校验的Hash算法与迭代次数
	 * 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行,init()方法之前执行。
	 */
	@PostConstruct
	public void initCredentialsMatcher(){
		setCredentialsMatcher(new RetryLimitHashedCredentialsMatcher());
	}
	
	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		public String id;
		public String loginName;
		public String schoolId;

		public ShiroUser(String id, String loginName,String schoolId) {
			this.id = id;
			this.loginName = loginName;
			this.schoolId = schoolId;
		}


		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return loginName;
		}

		/**
		 * 重载hashCode,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(loginName);
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ShiroUser other = (ShiroUser) obj;
			if (loginName == null) {
				if (other.loginName != null) {
					return false;
				}
			} else if (!loginName.equals(other.loginName)) {
				return false;
			}
			return true;
		}
	}
  
  
}  