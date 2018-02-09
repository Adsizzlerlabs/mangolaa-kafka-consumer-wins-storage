package com.adsizzler.mangolaa.kafkaconsumer.wins.storage.annotation

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Annotation to use on methods that are only meant for testing, and nothing else.
 * Created by ankushsharma on 05/02/18.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TestingOnly {

}