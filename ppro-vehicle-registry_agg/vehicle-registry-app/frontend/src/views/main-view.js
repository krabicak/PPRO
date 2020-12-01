import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-tabs/src/vaadin-tabs.js';
import '@vaadin/vaadin-tabs/src/vaadin-tab.js';
import '@polymer/iron-icon/iron-icon.js';
import './users-form.js';

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
<vaadin-vertical-layout style="width: 100%; height: 100%;" id="vaadinVerticalLayout">
 <vaadin-vertical-layout style="height: 100%; align-items: stretch; width: 100%;" id="verticalLayout">
  <vaadin-tabs id="tabNavigation" style="align-self: stretch; flex-grow: 0;" orientation="horizontal" selected="0">
   <vaadin-tab selected id="tabUserManagement">
    <iron-icon icon="lumo:user" id="ironIcon"></iron-icon>
    <span id="span">Správa uživatelů</span>
   </vaadin-tab>
   <vaadin-tab id="vaadinTab1">
    <iron-icon icon="lumo:cog" id="ironIcon1"></iron-icon>
    <span id="span1">Tab two</span>
   </vaadin-tab>
   <vaadin-tab id="vaadinTab2">
    <iron-icon icon="lumo:bell" id="ironIcon2"></iron-icon>
    <span id="span2">Tab three</span>
   </vaadin-tab>
  </vaadin-tabs>
  <users-form id="usersForm" style="align-self: stretch; flex-grow: 1; flex-shrink: 0;"></users-form>
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
