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

package org.gradle.execution.plan

import org.gradle.internal.snapshot.CaseSensitivity
import spock.lang.Specification

class RelatedLocationsTest extends Specification {
    def locations = new RelatedLocations(CaseSensitivity.CASE_SENSITIVE)

    def "parent is related to location"() {
        def node1 = Mock(Node)
        def node2 = Mock(Node)
        def node3 = Mock(Node)
        locations.recordRelatedToNode(node1, ["/some/location"])
        locations.recordRelatedToNode(node2, ["/some/other/location"])
        locations.recordRelatedToNode(node3, ["/some/other/third"])

        expect:
        assertNodesRelated("/some", node1, node2, node3)
        assertNodesRelated("/some/other", node2, node3)
        assertNodesRelated("/some/other/third", node3)
        assertNodesRelated("/some/other/third/sub/dir", node3)
        assertNodesRelated("/some/different")
    }

    def "ancestor is related to location"() {
        def node1 = Mock(Node)
        def node2 = Mock(Node)
        locations.recordRelatedToNode(node1, ["/some/location"])
        locations.recordRelatedToNode(node2, ["/some/location/within/deep"])

        expect:
        assertNodesRelated("/some/location/within", node2)
    }

    void assertNodesRelated(String location, Node... expectedNodeList) {
        def expectedNodes = expectedNodeList as Set
        assert locations.getNodesRelatedTo(location) == expectedNodes
        assert locations.getNodesRelatedTo(location) { element -> true } == expectedNodes
    }

}
