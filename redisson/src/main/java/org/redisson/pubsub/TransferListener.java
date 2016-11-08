/**
 * Copyright 2016 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redisson.pubsub;

import org.redisson.misc.RPromise;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

/**
 * 
 * @author Nikita Koksharov
 *
 * @param <T> type
 */
public class TransferListener<T> implements FutureListener<T> {

    private RPromise<T> promise;
    
    public TransferListener(RPromise<T> promise) {
        super();
        this.promise = promise;
    }

    @Override
    public void operationComplete(Future<T> future) throws Exception {
        if (!future.isSuccess()) {
            promise.tryFailure(future.cause());
            return;
        }
   
        promise.trySuccess(future.getNow());
    }
    
}