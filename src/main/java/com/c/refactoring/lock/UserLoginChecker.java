package com.c.refactoring.lock;

import java.util.Date;
import java.util.List;

import static com.c.refactoring.lock.Constants.LOCK_TEXT;

public class UserLoginChecker {

    private static final int MAXIMUM_LOCK_PERIOD_IN_MILLISECONDS = 3600000;

    public Lock isUserAllowedToLogin(boolean isFirstScreen, User userTryingToLogin, List existingLocks) {

        if (existingLocks.isEmpty()) return new Lock();

        Object[] existingLock = (Object[]) existingLocks.get(0);
        String userIdWithLock = getUserId(existingLock);
        String lockMsg = getLockMsg(userIdWithLock);

        if (isTimeToLockExpired(existingLock))
            return userInFirstScreenOrUserHasAccess(isFirstScreen, userTryingToLogin, userIdWithLock) ? getWriteLock() : getReadLock(lockMsg);

        return isSameUserTryingToLoginAgain(userTryingToLogin, userIdWithLock) ? getWriteLock() : getReadLock(lockMsg);

    }

    private boolean isSameUserTryingToLoginAgain(User userTryingToLogin, String userIdWithLock) {
        return userIdWithLock.equalsIgnoreCase(userTryingToLogin.getUserId());
    }

    private Lock getWriteLock() {
        Lock lock = new Lock();
        lock.setRead(false);
        return lock;
    }

    private Lock getReadLock(String lockMsg) {
        Lock lock = new Lock();
        lock.setRead(true);
        lock.setLockReason(lockMsg);
        return lock;
    }

    private boolean userInFirstScreenOrUserHasAccess(boolean firstScreen, User user, String userId) {
        return firstScreen || isSameUserTryingToLoginAgain(user, userId);
    }

    private boolean isTimeToLockExpired(Object[] existingLock) {
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