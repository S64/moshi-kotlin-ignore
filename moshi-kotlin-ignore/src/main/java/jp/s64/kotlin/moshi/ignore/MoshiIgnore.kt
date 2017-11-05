/*
 * Copyright (C) 2017 Shuma Yoshioka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.s64.kotlin.moshi.ignore

import com.squareup.moshi.*
import java.lang.reflect.Type

class IgnoreAdapter<T>(
        val delegate: JsonAdapter<T>
) : JsonAdapter<T>() {

    companion object {

        val FACTORY = object : Factory {

            override fun create(type: Type, annotations: Set<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
                if (annotations.any { it.annotationClass == Ignore::class })
                    return IgnoreAdapter<Any>(moshi.adapter(type, Types.nextAnnotations(annotations, Ignore::class.java)))
                return null
            }

        }
    }

    override fun toJson(writer: JsonWriter, value: T?) {
        delegate.toJson(writer, null)
    }

    override fun fromJson(reader: JsonReader): T? {
        reader.skipValue()
        return null
    }

}

@JsonQualifier
@MustBeDocumented
@Target(
        AnnotationTarget.VALUE_PARAMETER,
        AnnotationTarget.PROPERTY
)
@Retention(AnnotationRetention.RUNTIME)
annotation class Ignore
