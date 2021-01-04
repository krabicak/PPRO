import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-checkbox/src/vaadin-checkbox.js';
import '@vaadin/vaadin-grid/src/vaadin-grid.js';
import '@vaadin/vaadin-date-picker/src/vaadin-date-picker.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-field.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@polymer/iron-icon/iron-icon.js';

class VehicleForm extends PolymerElement {

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
  <vaadin-grid items="[[items]]" id="gridVehicles" style="flex-shrink: 0; width: 100%;"></vaadin-grid>
 </vaadin-vertical-layout>
 <vaadin-vertical-layout theme="spacing-s" id="vaadinVerticalLayoutUsers" style="width: 20%; height: 100%; padding: var(--lumo-space-s); margin: var(--lumo-space-s);">
  <vaadin-button id="buttonReset">
   Reset 
  </vaadin-button>
  <vaadin-text-field label="SPZ" placeholder="SPZ" id="fieldSpz" required></vaadin-text-field>
  <vaadin-text-field label="VIN" placeholder="VIN" id="fieldVin" required></vaadin-text-field>
  <vaadin-text-field label="Číslo malého TP" placeholder="Číslo malého TP" id="fieldSmallTechnical" required></vaadin-text-field>
  <vaadin-date-picker label="Splatnost malého TP" placeholder="Vyber datum" id="dateSmallTechnical" required></vaadin-date-picker>
  <vaadin-text-field label="Číslo velkého TP" placeholder="Číslo velkého TP" id="fieldBigTechnical" required></vaadin-text-field>
  <vaadin-date-picker label="Splatnost velkého TP" placeholder="Vyber datum" id="dateBigTechnical" required></vaadin-date-picker>
  <vaadin-text-field label="Jméno" placeholder="Jméno" id="fieldName" required></vaadin-text-field>
  <vaadin-text-field label="Přijmení" placeholder="Přijmení" id="fieldSurname" required></vaadin-text-field>
  <vaadin-text-field label="Rodné číslo" placeholder="Rodné číslo" id="fieldBornnum" required></vaadin-text-field>
  <vaadin-checkbox id="checkBoxActive">
    Aktivní 
  </vaadin-checkbox>
  <vaadin-button id="buttonEditVehicle">
   <iron-icon icon="lumo:edit" slot="prefix"></iron-icon>Upravit 
  </vaadin-button>
  <vaadin-button id="buttonAddVehicle" theme="primary success" style="align-self: flex-start;">
   <iron-icon icon="lumo:plus" slot="prefix"></iron-icon>Přidat 
  </vaadin-button>
 </vaadin-vertical-layout>
</vaadin-horizontal-layout>
`;
    }

    static get is() {
        return 'vehicle-form';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(VehicleForm.is, VehicleForm);
