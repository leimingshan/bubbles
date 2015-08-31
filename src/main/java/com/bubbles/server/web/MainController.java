package com.bubbles.server.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mingshan Lei
 * @since 2015/8/31.
 */
@RestController
public class MainController {

    @RequestMapping(value = "/minversion", method = RequestMethod.GET)
    public Version getMinVersion() {
        Version version = new Version();
        version.setForceUpdateMinVersion(1);
        version.setUrl("http://download.test.com/temp.apk");
        return version;
    }

    public class Version {

        public String url;

        public Integer forceUpdateMinVersion;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Integer getForceUpdateMinVersion() {
            return forceUpdateMinVersion;
        }

        public void setForceUpdateMinVersion(Integer forceUpdateMinVersion) {
            this.forceUpdateMinVersion = forceUpdateMinVersion;
        }
    }
}
