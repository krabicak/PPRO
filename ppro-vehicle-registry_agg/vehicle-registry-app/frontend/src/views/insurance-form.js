import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-grid/src/vaadin-grid.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-field.js';
import '@polymer/iron-icon/iron-icon.js';

class InsuranceForm extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                    width: 100%;
                }
            </style>
<vaadin-horizontal-layout class="content" style="width: 100%; height: 100%;" id="vaadinHorizontalLayoutUsers">
 <vaadin-vertical-layout theme="spacing" id="vaadinVerticalLayout" style="width: 80%; padding: var(--lumo-space-m);">
  <vaadin-grid items="[[items]]" id="gridInsurancies" style="flex-shrink: 0; width: 100%;"></vaadin-grid>
 </vaadin-vertical-layout>
 <vaadin-vertical-layout theme="spacing" id="vaadinVerticalLayoutUsers" style="width: 20%; height: 100%; padding: var(--lumo-space-m);">
  <vaadin-text-field label="Název pojišťovny" placeholder="Název pojišťovny" id="fieldInsurance"></vaadin-text-field>
  <vaadin-button id="buttonEditInsurance">
   <iron-icon icon="lumo:edit" slot="prefix"></iron-icon>Upravit 
  </vaadin-button>
  <vaadin-button id="ButtonDeleteInsurance">
   <iron-icon icon="lumo:cross" slot="prefix" id="ironIcon"></iron-icon>Smazat 
  </vaadin-button>
  <vaadin-button id="buttonAddInsurance" style="align-self: flex-start;">
   <iron-icon icon="lumo:plus" slot="prefix"></iron-icon>Přidat 
  </vaadin-button>
 </vaadin-vertical-layout>
</vaadin-horizontal-layout>
`;
    }

    static get is() {
        return 'insurance-form';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(InsuranceForm.is, InsuranceForm);
