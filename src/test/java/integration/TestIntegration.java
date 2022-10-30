package integration;

import base.Base;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.InetAddress;

public class TestIntegration extends Base {

    @Test
    public void testAppServerConnectivity() {
        try {
            InetAddress host = InetAddress.getByName(prop.getProperty("ip"));
            if (host.isReachable(Integer.parseInt(prop.getProperty("driver_timeout")))) {
                System.out.println("HOST RESPONSE: 200");
                Assert.assertTrue(true);
            }
        } catch (IOException e) {
            System.out.println("HOST IS UNREACHABLE");
        }

        Assert.fail();
    }

}
