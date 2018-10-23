package core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import pages.BasePage;

@Configuration
@ComponentScan(basePackages = { "pages" })
public class AppConfig extends BasePage {

}
