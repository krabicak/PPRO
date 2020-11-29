import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@polymer/iron-icon/iron-icon.js';
import '@vaadin/vaadin-tabs/src/vaadin-tabs.js';
import '@vaadin/vaadin-tabs/src/vaadin-tab.js';

class AdminPage extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
<vaadin-vertical-layout style="width: 100%; height: 100%;">
 <vaadin-tabs style="align-self: stretch;" orientation="horizontal" selected="0">
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
</vaadin-vertical-layout>
`;
    }

    static get is() {
        return 'admin-page';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(AdminPage.is, AdminPage);
