package org.crealytics.exception;

public interface ExceptionCode {
    /*Using range 4xx to easily map with http level errorcode*/
    int INTERNAL_SERVER_EXECPTION = 410;
    int INVALID_MONTH = 411;
    int INVALID_SITE = 412;
    int NO_RECORD_FOUND = 413;
}
