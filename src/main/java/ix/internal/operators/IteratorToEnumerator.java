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

import ix.Enumerator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class IteratorToEnumerator<T> implements Enumerator<T> {
	private final Iterator<? extends T> it;
	/** The current value. */
	T value;
	/** The current value is set. */
	boolean hasValue;

	public IteratorToEnumerator(Iterator<? extends T> it) {
		this.it = it;
	}

	@Override
	public T current() {
	    if (hasValue) {
	        return value;
	    }
	    throw new NoSuchElementException();
	}

	@Override
	public boolean next() {
	    if (it.hasNext()) {
	        value = it.next();
	        hasValue = true;
	        return false;
	    }
	    hasValue = false;
	    return false;
	}
}