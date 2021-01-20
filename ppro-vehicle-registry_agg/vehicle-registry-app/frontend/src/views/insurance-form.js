import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-grid/src/vaadin-grid.js';
import '@vaadin/vaadin-date-picker/src/vaadin-date-picker.js';
import '@vaadin/vaadin-combo-box/src/vaadin-combo-box.js';
import '@polymer/iron-icon/iron-icon.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-field.js';

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
  <vaadin-text-field placeholder="Hledat" id="fieldSearch">
   <iron-icon icon="lumo:search" slot="prefix"></iron-icon>
  </vaadin-text-field>
  <vaadin-grid items="[[items]]" id="gridInsurancies" style="flex-shrink: 0; width: 100%;"></vaadin-grid>
 </vaadin-vertical-layout>
 <vaadin-vertical-layout theme="spacing-s" id="vaadinVerticalLayoutUsers" style="width: 20%; height: 100%; padding: var(--lumo-space-s); margin: var(--lumo-space-s);">
  <vaadin-button id="buttonReset">
   <iron-icon icon="lumo:cross" slot="prefix"></iron-icon>Reset 
  </vaadin-button>
  <vaadin-date-picker label="Od" placeholder="Od" id="dateFrom"></vaadin-date-picker>
  <vaadin-date-picker label="Do" placeholder="Do" id="dateTo"></vaadin-date-picker>
  <vaadin-combo-box id="selectVehicle" label="Vozidlo" clear-button-visible></vaadin-combo-box>
  <vaadin-combo-box id="selectInsurancerEmployee" label="Pojišťovák"></vaadin-combo-box>
  <vaadin-text-field label="Rodné číslo" placeholder="Rodné číslo" id="fieldBornnum"></vaadin-text-field>
  <vaadin-text-field label="Jméno" placeholder="Jméno" id="fieldName"></vaadin-text-field>
  <vaadin-text-field label="Příjmení" placeholder="Příjmení" id="fieldSurname"></vaadin-text-field>
  <vaadin-button id="buttonEditInsurance">
   <iron-icon icon="lumo:edit" slot="prefix"></iron-icon>Upravit 
  </vaadin-button>
  <vaadin-button id="buttonDeleteInsurance" theme="primary error">
   <iron-icon icon="lumo:cross" slot="prefix" id="ironIcon"></iron-icon>Smazat 
  </vaadin-button>
  <vaadin-button id="buttonAddInsurance" style="align-self: flex-start;" theme="primary success">
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
