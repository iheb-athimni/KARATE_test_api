package UiTestPrototype.configs;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VariableConfig {

    public VariableConfig(){

    }
    @Value("${navigator}")
    private String navigator;

    public void setBrowser(String theBrowser)
    {
        this.navigator= theBrowser;
    }

    public String getBrowser()
    {
        return navigator;
    }



}
