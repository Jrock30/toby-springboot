package com.jrock.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
//        return new String[] { // 임포트할 클래스
//                "com.jrock.config.autoconfig.DispacherServletConfig",
//                "com.jrock.config.autoconfig.TomcatWebServerConfig"
//        };

//        Iterable<String> candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
//        return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);

        List<String> autoConfigs = new ArrayList<>();

//        for (String candidate : ImportCandidates.load(MyAutoConfiguration.class, classLoader)) {
//            autoConfigs.add(candidate);
//        }

        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);

//        return autoConfigs.stream().toArray(String[]::new);
//        return Arrays.copyOf(autoConfigs.toArray(), autoConfigs.size(), String[].class);
        return autoConfigs.toArray(new String[0]);
    }
}
