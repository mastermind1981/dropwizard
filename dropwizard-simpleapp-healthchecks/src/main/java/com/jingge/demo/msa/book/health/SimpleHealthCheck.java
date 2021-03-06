package com.jingge.demo.msa.book.health;

import com.codahale.metrics.health.HealthCheck;
import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class SimpleHealthCheck extends HealthCheck {

    private int minMemory;

    public SimpleHealthCheck(int minMemory) {
        this.minMemory = minMemory;
    }

    @Override
    protected Result check() throws Exception {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        long pmSize = osBean.getTotalPhysicalMemorySize() / 1000000000;
        System.out.println(pmSize);

        return pmSize < minMemory ? Result.unhealthy("At least " + minMemory + "G RAM is required to make this application stable") : Result.healthy();
    }
}
