package com.jdlozanom.simplerssreader.api.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "rss", strict = false)

public class Feed implements Serializable {

    @Element(name = "channel")
    private ChannelFeed channelFeed;

    public Feed() {
    }

    public Feed(ChannelFeed channelFeed) {
        this.channelFeed = channelFeed;
    }

    public ChannelFeed getChannelFeed() {
        return channelFeed;
    }
}
