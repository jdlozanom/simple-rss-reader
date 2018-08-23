package com.jdlozanom.simplerssreader.api.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "channel", strict = false)
public class ChannelFeed implements Serializable {

    @ElementList(inline = true, name = "item")
    public List<ItemFeed> items;

    public ChannelFeed() {
    }

    public ChannelFeed(List<ItemFeed> items) {
        this.items = items;
    }

    public List<ItemFeed> getItems() {
        return items;
    }
}
