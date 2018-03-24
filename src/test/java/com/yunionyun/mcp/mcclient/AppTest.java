package com.yunionyun.mcp.mcclient;


import com.alibaba.fastjson.JSONObject;
import com.yunionyun.mcp.mcclient.keystone.TokenCredential;
import com.yunionyun.mcp.mcclient.managers.ListResult;
import com.yunionyun.mcp.mcclient.managers.impl.ImageManager;
import com.yunionyun.mcp.mcclient.managers.impl.NotifyManager;
import com.yunionyun.mcp.mcclient.managers.impl.ProjectManager;
import com.yunionyun.mcp.mcclient.managers.impl.RoleAssignmentManager;
import com.yunionyun.mcp.mcclient.managers.impl.RoleManager;
import com.yunionyun.mcp.mcclient.managers.impl.ServerDiskManager;
import com.yunionyun.mcp.mcclient.managers.impl.ServerManager;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        Client cli = new Client("http://10.168.26.235:5000/v3", 1000, true, true);
        try {
        		TokenCredential token = cli.Authenticate("sysadmin", "sysadmin", "Default", "system");
        		Session s = cli.newSession("TestLocal", null, null, token);
        		ServerManager mgr = new ServerManager();
        		System.out.println("Start List");
        		JSONObject srvquery = new JSONObject();
        		srvquery.put("details", true);
        		srvquery.put("with_meta", true);
        		s.setTaskNotifyUrl("http://10.168.26.235:7777");
        		ListResult result = mgr.List(s, srvquery);
        		System.out.println(result.toString());
        		JSONObject srv = result.getDataAt(0);
        		if (srv != null) {
        			s.setTaskNotifyUrl("http://10.168.26.235:8888");
        			String id = srv.getString("id");
        			JSONObject srv2 = mgr.Get(s, id, null);
        			System.out.println(srv2.toJSONString());
        			
        			ServerDiskManager srvdiskman = new ServerDiskManager();
        			ListResult serverdisks = srvdiskman.LisDescendent(s, id, null);
        			System.out.println(serverdisks.toString());
        		}
        		ImageManager imgman = new ImageManager();
        		JSONObject imgquery = new JSONObject();
        		imgquery.put("details", true);
        		s.setTaskNotifyUrl("http://10.168.26.235:8888");
        		ListResult imgs = imgman.List(s, imgquery);
        		System.out.println(imgs.toString());
        		
        		ProjectManager projman = new ProjectManager();
        		JSONObject proj = projman.GetByName(s, "newproject", null);
        		RoleManager roleman = new RoleManager();
        		JSONObject role = roleman.GetByName(s, "project_owner", null);
        		if (role != null) {
        			JSONObject rolequery = new JSONObject();
        			rolequery.put("role.id", role.getString("id"));
        			rolequery.put("project.id", proj.getString("id"));
        			rolequery.put("effective", "true");
        			rolequery.put("include_names", "true");
        			RoleAssignmentManager roleassignman = new RoleAssignmentManager();
        			ListResult assignments = roleassignman.List(s, rolequery);
        			for (int i = 0; i < assignments.getDataSize(); i ++) {
        				JSONObject ass = assignments.getDataAt(i);
        				System.out.print(ass);
        			}
        		}
        		
        		NotifyManager notifyman = new NotifyManager();
        		notifyman.notify(s, "qiujian", NotifyManager.CONTACT_EMAIL, "test", NotifyManager.PRIORITY_NORMAL, "This is a normal message");

        		System.out.println("Test complete!!!");
        }catch(Exception e) {
        		e.printStackTrace();
        		System.out.print("Client error: " + e);
        }
    }
}
