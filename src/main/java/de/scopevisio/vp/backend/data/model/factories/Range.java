package de.scopevisio.vp.backend.data.model.factories;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Range<T> {

    protected T lower;
    protected T upper;

    public abstract boolean isRange(T value);

}
