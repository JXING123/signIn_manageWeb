package com.cn.tonyou.filter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.cn.tonyou.pojo.SysUserInfo;
public class LoginFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;      
        HttpServletResponse response  =(HttpServletResponse)arg1;       
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        List<String> path = new ArrayList<String>();
        path.add("getUserByIdAllorder");
        path.add("toCar");
        path.add("userUpdateGo");
        path.add("orderApprais");
        path.add("myHok");
        path.add("buyGoods");
        path.add("addGoodscar");
        path.add("userUpdateGo");
        path.add("qianggou");
        path.add("addCollection");
        //得到请求地址 ַ
        String uri = request.getRequestURI();
        //获取请求的发布名
        //String path = request.getContextPath();
        String file[] = uri.split("/");	//通过/分隔，这样，数组的最后一个值，就是页面，
        //获取到请求的action
        //String action = uri.substring(uri.indexOf(path));
        String action =  "";
        if(file.length>=1){
        	action = file[file.length-1];
        }
        //获取登录的对象
        SysUserInfo user = (SysUserInfo) session.getAttribute("user");
        if(path.contains(action)){
        	if(user!=null){
            	arg2.doFilter(arg0, arg1);
            	
//            	Object paths = session.getAttribute("paths");
//            	int j=0;
//            	if(paths != null){
//            		List<String> paths1 = (List<String>) paths;
//        			boolean flag = false;
//        			for(String path1 : paths1){
//        				j++;
//        				if(null!=path1){
//        					String[] p = path1.split(",");
//            				for(int i=0;i<p.length;i++){
//            					if(p[i].trim().equals(action.trim())){
//            						flag= true;
//            					}
//            				}
//        				}
//        			}
//            	} 
            	return;
            }else{
            	response.sendRedirect("goLogin");
            	return;
            }
        }
        arg2.doFilter(arg0, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
