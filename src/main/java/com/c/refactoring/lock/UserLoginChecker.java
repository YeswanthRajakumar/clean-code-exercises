package com.c.refactoring.lock;

import java.util.Date;
import java.util.List;

import static com.c.refactoring.lock.Constants.LOCK_TEXT;

public class UserLoginChecker {

    private static final int MAXIMUM_LOCK_PERIOD_IN_MILLISECONDS = 3600000;

    public Lock isUserAllowedToLogin(boolean isFirstScreen, User userTryingToLogin, List<Object> existingLocks) {

        if (existingLocks.isEmpty()) return new Lock();

        Object[] existingLock = (Object[]) existingLocks.get(0);
        String userIdWithLock = getUserId(existingLock);

        if (isSameUserTryingToLoginAgain(userTryingToLogin, userIdWithLock)) return getWriteLock();

        if (isTimeToLockIsExpired(existingLock) && isFirstScreen) return getWriteLock();

        return getReadLockWithLockMessage(userIdWithLock);

    }

    private boolean isSameUserTryingToLoginAgain(User userTryingToLogin, String userIdWithLock) {
        return userIdWithLock.equalsIgnoreCase(userTryingToLogin.getUserId());
    }

    private Lock getWriteLock() {
        Lock lock = new Lock();
        lock.setRead(false);
        return lock;
    }

    private Lock getReadLockWithLockMessage(String userIdWithLock) {
        Lock lock = new Lock();
        lock.setRead(true);
        String lockMsg = getLockMsg(userIdWithLock);
        lock.setLockReason(lockMsg);
        return lock;
    }


    private boolean isTimeToLockIsExpired(Object[] existingLock) {
        return getGetCurrentTime() - getLockTimestamp(existingLock) > MAXIMUM_LOCK_PERIOD_IN_MILLISECONDS;
    }

    private long getGetCurrentTime() {
        Date time = new Date();
        return time.getTime();
    }

    private String getLockMsg(String userId) {
        return LOCK_TEXT.replace("@@USER@@", userId);
    }

    private long getLockTimestamp(Object[] access) {
        return ((Date) access[1]).getTime();
    }

    private String getUserId(Object[] access) {
        return (String) access[0];
    }
}