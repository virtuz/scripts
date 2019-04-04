#!/usr/bin/env groovy

/**
 * Just logging, print time before the message
 * References:
 *   <a href="http://docs.groovy-lang.org/docs/next/html/documentation/design-pattern-in-groovy.html#_traditional_example">Example of logging</a>
 *
 * @param message the message to print
 *
 * @maintainer virtuz.blr@gmail.com
 */
void log(message){
    def now = Calendar.instance
    println "${now.time}: ${message}"
}

/**
 * Be curious and not shy away from source diving.
 * Determine a class and and list methods from an instance.
 * References:
 *   <a href="https://www.youtube.com/watch?v=qaUPESDcsGg">Jenkins World 2017: Mastering the Jenkins Script Console</a>
 *
 * @param thing the class instance
 *
 * @maintainer virtuz.blr@gmail.com
 */
void getClassNameAndMethods(thing){
    log("getClassNameAndMethods(${thing})")
    log(thing.getClass().getName())
    log(thing.metaClass.methods*.name.sort().unique())
}

