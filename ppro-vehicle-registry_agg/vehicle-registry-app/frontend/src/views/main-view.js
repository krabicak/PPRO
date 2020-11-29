import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-tabs/src/vaadin-tabs.js';
import '@vaadin/vaadin-tabs/src/vaadin-tab.js';
import '@vaadin/vaadin-grid/src/vaadin-grid.js';
import '@vaadin/vaadin-grid/src/vaadin-grid-column.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@polymer/iron-icon/iron-icon.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';

class MainView extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
<vaadin-vertical-layout style="width: 100%; height: 100%; align-items: flex-end;" id="textLoggedUser">
 <vaadin-button id="buttonLogout" style="align-self: flex-end;">
  <iron-icon icon="lumo:arrow-right" slot="prefix"></iron-icon>Odhlásit se
 </vaadin-button>
 <vaadin-tabs style="align-self: stretch; flex-grow: 0;" orientation="horizontal" selected="0">
  <vaadin-tab>
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
 <vaadin-grid items="[[items]]" id="gridUsers" style="flex-grow: 0; flex-shrink: 1;">
  <vaadin-grid-column width="50px" flex-grow="0">
   <template class="header">
     # 
   </template>
   <template>
     [[index]] 
   </template>
   <template class="footer">
     # 
   </template>
  </vaadin-grid-column>
  <vaadin-grid-column>
   <template class="header">
     Value 
   </template>
   <template>
     [[item.value]] 
   </template>
   <template class="footer">
     Value 
   </template>
  </vaadin-grid-column>
 </vaadin-grid>
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
