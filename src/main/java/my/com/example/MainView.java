package my.com.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vaadin.flow.component.combobox.ComboBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a template element.
 */
@HtmlImport("styles/shared-styles.html")
@Route("")
public class MainView extends VerticalLayout {
    private static final Logger LOG = LoggerFactory.getLogger(MainView.class);
    
    private GridOne gridOne;
    private GridTwo gridTwo;
    ComboBox<String> c;

    boolean v = false;
    public MainView() {


        initTabSheet();
        
        setClassName("main-layout");
    }
    
    private void initTabSheet() {
        
        Tab tab1 = new Tab("Grid 1");
        Div page1 = new Div();
        page1.add(getGridOne());
        
        Tab tab2 = new Tab("Grid 2");
        Div page2 = new Div();
        page2.add(getGridTwo());



        Map<Tab, Component> tabsToPages = new HashMap<>();

        tabsToPages.put(tab1, page1);
        tabsToPages.put(tab2, page2);
        
        Tabs tabs = new Tabs(tab1, tab2);
        tabs.setFlexGrowForEnclosedTabs(1);

        Div pages = new Div(page1, page2);
        pages.setSizeFull();

        // hide all the pages
        hideAllPages(tabsToPages.values());

        // select and show page 1
        tabs.setSelectedTab(tab1);
        showPage(page1);

        tabs.addSelectedChangeListener(event -> {
            hideAllPages(tabsToPages.values());

            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            showPage(selectedPage);
        });
        
        add(tabs, pages);

    }

    public void hideAllPages(Collection<Component> pages){
        for(Component p : pages){
            p.getElement().getStyle().set("display","none");
        }
    }

    public void showPage(Component selectedPage){
        selectedPage.getElement().getStyle().set("display","flex");
    }

    private GridOne getGridOne() {
        if (gridOne == null) {
            gridOne = new GridOne();
        }
        
        return gridOne;
    }
    
    private GridTwo getGridTwo() {
        if (gridTwo == null) {
            gridTwo = new GridTwo();
        }
        return gridTwo;
    }
    
}
