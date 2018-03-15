// ISecurityCenter.aidl
package com.lucidastar.chapter_2.binderpool;

// Declare any non-default types here with import statements

interface ISecurityCenter {
    String encrypt(String content);
    String decrypt(String password);
}
