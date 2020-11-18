/*
 * Copyright 2020 the original author or authors.
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

package org.gradle.internal.fingerprint;

/**
 * Specifies how a fingerprinter should handle directories that are found in a filecollection.
 */
public enum DirectorySensitivity {
    // Whatever the default behavior is for the given fingerprinter.  For some fingerprinters, the
    // default behavior is to fingerprint directories, for others, they ignore directories by default.
    DEFAULT,
    // Ignore empty directories
    IGNORE_DIRECTORIES
}