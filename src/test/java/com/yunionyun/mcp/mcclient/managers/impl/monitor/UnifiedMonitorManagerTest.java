package com.yunionyun.mcp.mcclient.managers.impl.monitor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yunionyun.mcp.mcclient.Client;
import com.yunionyun.mcp.mcclient.EndpointType;
import com.yunionyun.mcp.mcclient.JSONClientException;
import com.yunionyun.mcp.mcclient.Session;
import com.yunionyun.mcp.mcclient.common.McClientJavaBizException;
import com.yunionyun.mcp.mcclient.keystone.TokenCredential;
import junit.framework.TestCase;

import java.io.IOException;

public class UnifiedMonitorManagerTest extends TestCase {

	public void test() {
//		Client cli = new Client("https://192.168.222.171:30357/v3", 5000, true, true);
//		try {
//			TokenCredential token = cli.Authenticate("sysadmin", "ixtPb1Z3_crqS8YG", "", "system", "");
//			UnifiedMonitorManager man = new UnifiedMonitorManager();
//			Session session = cli.newSession("YunionHQ", null, EndpointType.PublicURL, token);
//			MetricQueryInput input = MetricQueryInput.newQuery("1h", "5m");
//			input.addMetric("vm_cpu", "usage_active", "vm_id", "021e9e2b-ae4e-4bc9-8a29-d80225db379e");
//			JSONObject jsonObject = man.PerformQuery(session, input);
//			System.out.printf("jsonObject: " + jsonObject.toJSONString(jsonObject, SerializerFeature.PrettyFormat));
//		} catch (JSONClientException e) {
//			throw new RuntimeException(e);
//		} catch (McClientJavaBizException e) {
//			throw new RuntimeException(e);
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
	}

}