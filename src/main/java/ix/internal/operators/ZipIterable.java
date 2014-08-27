/*
 * Copyright 2011-2014 David Karnok
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ix.internal.operators;

import ix.internal.util.SingleContainer;

import java.util.Iterator;
import java.util.NoSuchElementException;

import rx.Notification;
import rx.functions.Func2;

/**
 * Iterable sequence containing the zipped values of two other sequences.
 *
 * @param <T> the first source value type
 * @param <U> the second source value type
 * @param <V> the result value type
 */
public final class ZipIterable<T, U, V> implements Iterable<V> {
	/** The first source sequence. */
	private final Iterable<? extends T> left;
	/** The second source sequence. */
	private final Iterable<? extends U> right;
	/** The function that combines values from the two sources. */
	private final Func2<? super T, ? super U, ? extends V> combiner;

	/**
	 * Constructor, initializes the fields.
	 * @param left the first sequence
	 * @param right the second sequence
	 * @param combiner the combinator function
	 */
	public ZipIterable(Iterable<? extends T> left, Iterable<? extends U> right,
			Func2<? super T, ? super U, ? extends V> combiner
			) {
		this.right = right;
		this.combiner = combiner;
		this.left = left;
	}

	@Override
	public Iterator<V> iterator() {
	    return new Iterator<V>() {
	        /** The left iterator. */
	        final Iterator<? extends T> ts = left.iterator();
	        /** The right iterator. */
	        final Iterator<? extends U> us = right.iterator();
	        /** The peek-ahead container. */
	        final SingleContainer<Notification<? extends V>> peek = new SingleContainer<Notification<? extends V>>();
	        @Override
	        public boolean hasNext() {
	            if (peek.isEmpty()) {
	                try {
	                    if (ts.hasNext() && us.hasNext()) {
	                        peek.add(Interactive.some(combiner.call(ts.next(), us.next())));
	                    }
	                } catch (Throwable t) {
	                    peek.add(Interactive.<V>err(t));
	                }
	            }
	            return !peek.isEmpty();
	        }
	        @Override
	        public V next() {
	            if (hasNext()) {
	                return Interactive.value(peek.take());
	            }
	            throw new NoSuchElementException();
	        }
	        @Override
	        public void remove() {
	            throw new UnsupportedOperationException();
	        }
	    };
	}
}