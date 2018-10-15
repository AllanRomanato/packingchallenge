package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
/**
 * Just an interface to keep the methods with the same name in case of reusing it
 * @author Allan 
 *
 * @param <T>
 * @param <V>
 */
public interface Creator<T,V>  {
	T create(V v) throws APIException;
}
