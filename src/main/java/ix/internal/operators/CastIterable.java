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

import java.util.Iterator;

public final class CastIterable<T> implements Iterable<T> {
	/** The source sequence. */
	private final Iterable<?> source;
	private final Class<T> token;

	public CastIterable(Iterable<?> source, Class<T> token) {
		this.source = source;
		this.token = token;
	}

	@Override
	public Iterator<T> iterator() {
	    return new Iterator<T>() {
	        /** The source iterator. */
	        final Iterator<?> it = source.iterator();
	        @Override
	        public boolean hasNext() {
	            return it.hasNext();
	        }
	        
	        @Override
	        public T next() {
	            return token.cast(it.next());
	        }
	        
	        @Override
	        public void remove() {
	            it.remove();
	        }
	        
	    };
	}
}