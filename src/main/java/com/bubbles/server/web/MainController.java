package com.bubbles.server.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author Mingshan Lei
 * @since 2015/8/31.
 */
@RestController
public class MainController {

    @RequestMapping(value = "/minversion", method = RequestMethod.GET)
    public Version getMinVersion() {
        Version version = new Version();
        version.setForceUpdateMinVersion(1);
        return version;
    }

    public class Version {

        public Integer forceUpdateMinVersion;

        public Integer getForceUpdateMinVersion() {
            return forceUpdateMinVersion;
        }

        public void setForceUpdateMinVersion(Integer forceUpdateMinVersion) {
            this.forceUpdateMinVersion = forceUpdateMinVersion;
        }
    }
}
