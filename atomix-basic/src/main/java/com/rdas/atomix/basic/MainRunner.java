package com.rdas.atomix.basic;

import io.atomix.cluster.Node;
import io.atomix.cluster.discovery.BootstrapDiscoveryProvider;
import io.atomix.core.Atomix;
import io.atomix.protocols.raft.partition.RaftPartitionGroup;
import io.atomix.storage.StorageLevel;
import io.atomix.utils.net.Address;
//import io.atomix.storage.StorageLevel;
//import io.atomix.utils.net.Address;
//import io.atomix.AtomixReplica;
//import io.atomix.catalyst.transport.Address;
//import io.atomix.catalyst.transport.netty.NettyTransport;
//import io.atomix.copycat.server.storage.Storage;
//import io.atomix.copycat.server.storage.StorageLevel;

import java.io.File;

public class MainRunner {

    public static void main(String[] args) {
        System.out.println("MainRunner Running");
        Storage storage = Storage.builder()
                .withDirectory(new File("log"))
                .withStorageLevel(StorageLevel.DISK)
                .build();
        AtomixReplica replica = AtomixReplica.builder(new Address("localhost", 8700))
                .withStorage(storage)
                .withTransport(new NettyTransport())
                .build();

        CompletableFuture<AtomixReplica> completableFuture = replica.bootstrap();
        completableFuture.join();

        /*
        Atomix atomix = Atomix.builder()
                .withMemberId("member1")
                .withNodeDiscovery(BootstrapDiscoveryProvider.builder()
                        .withNodes(
                                Node.builder()
                                        .withId("member1")
                                        .withAddress("10.192.19.181:5679")
                                        .build(),
                                Node.builder()
                                        .withId("member2")
                                        .withAddress("10.192.19.182:5679")
                                        .build(),
                                Node.builder()
                                        .withId("member3")
                                        .withAddress("10.192.19.183:5679")
                                        .build())
                        .build())
                .withManagementGroup(RaftPartitionGroup.builder("system")
                        .withNumPartitions(1)
                        .withMembers("member1", "member2", "member3")
                        .build())
                .withPartitionGroups(RaftPartitionGroup.builder("raft")
                        .withPartitionSize(3)
                        .withNumPartitions(3)
                        .withMembers("member1", "member2", "member3")
                        .build())
                .build();
         */

    }
}
