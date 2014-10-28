package org.telepatch.android;

import org.telepatch.messenger.TLRPC;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureSearch implements Future<TLRPC.messages_Messages> {

    private final CountDownLatch countDownLatch;
    private volatile boolean cancelled = false;
    private TLRPC.messages_Messages result = null;



    public FutureSearch() {

         countDownLatch = new CountDownLatch(1);


    }
   @Override
    public boolean isCancelled() {
        if (isDone()) {
            return false;
        } else {
            countDownLatch.countDown();
            cancelled = true;
            return !isDone();
        }
    }

    @Override
    public boolean isDone() {
        return countDownLatch.getCount() == 0;
    }

    @Override
    public TLRPC.messages_Messages get() throws InterruptedException, ExecutionException {
        countDownLatch.await();
        return result;
    }

    @Override
    public TLRPC.messages_Messages get(long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        countDownLatch.await(l, timeUnit);
        return result;
    }

    public void onMessagesSearchResult(final TLRPC.messages_Messages result) {
        if (result == null) {
            cancel(true);
        } else {
            this.result = result;
            countDownLatch.countDown();
        }


    }
    @Override
    public boolean cancel(boolean b) {
        cancelled = b;
        if (b == true) {
            countDownLatch.countDown();
        }
        return b;
    }

    }