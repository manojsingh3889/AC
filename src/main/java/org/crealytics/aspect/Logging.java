package org.crealytics.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
class GateKeeper {
    private static final Logger LOGGER = LogManager.getLogger(GateKeeper.class);

//    @Around("com.app.config.aspect.SystemArchitecture.inWebLayer()")
//    public Object apiCallTrail(ProceedingJoinPoint point) throws Throwable{
//        MethodSignature signature = (MethodSignature) point.getSignature();
//        RequestMapping methodAnnotation =  signature.getMethod().getAnnotation(RequestMapping.class);
//        RequestMapping clazzAnnotation = signature.getMethod().getDeclaringClass().getAnnotation(RequestMapping.class);
//        String urlCalled = Utility.safeValue(clazzAnnotation.value()[0], "<unknown>")+Utility.safeValue(methodAnnotation.value()[0], "<unknown>");
//
//        //auditing
//        log.info("Called Url[ "+urlCalled+" ] :: accessing Entering mehtod "+point.getSignature().toShortString());
//        long starttime = System.currentTimeMillis();
//
//        Object obj = point.proceed();
//        log.info("Url[ "+urlCalled+" ] ::: Leaving mehtod "+point.getSignature().toShortString()+" [Time taken "+(System.currentTimeMillis()-starttime)+"ms]");
//        return obj;
//    }
}
