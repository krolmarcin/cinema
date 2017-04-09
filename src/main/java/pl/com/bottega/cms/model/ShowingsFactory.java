package pl.com.bottega.cms.model;

import pl.com.bottega.cms.model.commands.CreateShowingsCommand;

/**
 * Created by maciek on 09.04.2017.
 */
public class ShowingsFactory {

    public ShowingsFactory(){

    }

    public Showing createShowings(CreateShowingsCommand cmd){
        return new Showing(cmd);
    }
}
