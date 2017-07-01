package com.pitstop.mobilecarwash.entity;

import org.springframework.http.HttpStatus;

/**
 * Created by Emmie on 2017/04/07.
 */
public class Result {

        private String message;
        private Boolean ok;
        private HttpStatus status;

        private Result(String message, Boolean ok, HttpStatus status) {
            this.message = message;
            this.ok = ok;
            this.status = status;
        }

        public static Result successful( String message, HttpStatus status) {
            return new Result(message, true, status);
        }

        public static Result failure( String message, HttpStatus status) {
            return new Result(message, false, status);
        }

        public String getMessage() {
            return message;
        }

        public Boolean isOk() {
            return ok;
        }

        public HttpStatus getStatus() {
            return status;
        }

        public static Result successful() {
            return new Result("", true, HttpStatus.OK);
        }
    }

