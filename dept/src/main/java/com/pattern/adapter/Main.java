package com.pattern.adapter;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int count = 1;
        Deque<RequestFuture<CommonResponse>> inFlightRequest = new ArrayDeque<>();

        RequestFuture<CommonResponse> requestFuture = new RequestFuture<>();
        requestFuture.addListener(new RequestFutureListener<CommonResponse>() {
            @Override
            public void onSuccess(CommonResponse response) {
                System.out.println("RequestFutureListener<CommonResponse>.onSuccess " + response);
            }
        });

        inFlightRequest.addFirst(requestFuture);
        send(requestFuture);

        // RequestFuture<CommonResponse> 转成 RequestFuture<Integer>，RequestFuture<CommonResponse> 的处理过程是 RequestFuture<Integer> 的子集
        // 即 RequestFuture<Integer> 复用了 RequestFuture<CommonResponse> 的处理流程
        RequestFuture<Integer> adaptedRequestFuture = requestFuture.compose(new RequestFutureAdapter<CommonResponse, Integer>() {
            @Override
            public void onSuccess(CommonResponse response, RequestFuture<Integer> requestFuture) {
                System.out.println("adaptedRequestFuture.onSuccess " + response.getValue());
                requestFuture.setValue(Integer.valueOf(response.getValue().substring(0, 3)));
            }
        });


        for (;;) {
            System.out.println("执行业务 " + count + " 中。。。");
            Thread.sleep(1000);
            System.out.println("执行业务 " + count++ + " 完毕。。。");
            Thread.sleep(1000);

            if (requestFuture.isDone() && inFlightRequest.contains(requestFuture)) {
                inFlightRequest.pollFirst();
                System.out.println("adaptedRequestFuture.getValue = " + adaptedRequestFuture.getValue());
            }
        }
    }

    public static void send(RequestFuture<CommonResponse> requestFuture) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5 * 1000);
                    requestFuture.complete(new CommonResponse("233_str"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
