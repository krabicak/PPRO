import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@polymer/iron-icon/iron-icon.js';
import '@vaadin/vaadin-tabs/src/vaadin-tabs.js';
import '@vaadin/vaadin-tabs/src/vaadin-tab.js';
import './users-page.js';

class MainView extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                    width: 100%;
                }
            </style>
<vaadin-vertical-layout style="width: 100%; height: 100%;">
 <vaadin-vertical-layout style="height: 100%; align-items: stretch; width: 100%;" id="verticalLayout">
  <vaadin-button id="buttonLogout" style="align-self: stretch;">
   <iron-icon icon="lumo:arrow-right" slot="prefix"></iron-icon>Odhlásit se 
  </vaadin-button>
  <vaadin-tabs id="vaadinTabs" style="align-self: stretch; flex-grow: 0;" orientation="horizontal" selected="0">
   <vaadin-tab selected>
    <iron-icon icon="lumo:user"></iron-icon>
    <span>Správa uživatelů</span>
   </vaadin-tab>
   <vaadin-tab>
    <iron-icon icon="lumo:cog"></iron-icon>
    <span>Tab two</span>
   </vaadin-tab>
   <vaadin-tab>
    <iron-icon icon="lumo:bell"></iron-icon>
    <span>Tab three</span>
   </vaadin-tab>
  </vaadin-tabs>
  <users-page id="usersPage" style="align-self: stretch;"></users-page>
 </vaadin-vertical-layout>
</vaadin-vertical-layout>
`;
    }

    static get is() {
        return 'main-view';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(MainView.is, MainView);
