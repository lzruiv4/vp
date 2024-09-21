import { unsafeCSS, registerStyles } from '@vaadin/vaadin-themable-mixin/register-styles';

import vaadinTimePickerCss from 'themes/linkki/components/vaadin-time-picker.css?inline';
import vaadinTextFieldCss from 'themes/linkki/components/vaadin-text-field.css?inline';
import vaadinTextAreaCss from 'themes/linkki/components/vaadin-text-area.css?inline';
import vaadinRadioButtonCss from 'themes/linkki/components/vaadin-radio-button.css?inline';
import vaadinPasswordFieldCss from 'themes/linkki/components/vaadin-password-field.css?inline';
import vaadinNotificationCardCss from 'themes/linkki/components/vaadin-notification-card.css?inline';
import vaadinMultiSelectComboBoxCss from 'themes/linkki/components/vaadin-multi-select-combo-box.css?inline';
import vaadinInputContainerCss from 'themes/linkki/components/vaadin-input-container.css?inline';
import vaadinGridCss from 'themes/linkki/components/vaadin-grid.css?inline';
import vaadinFormLayoutCss from 'themes/linkki/components/vaadin-form-layout.css?inline';
import vaadinFormItemCss from 'themes/linkki/components/vaadin-form-item.css?inline';
import vaadinDialogOverlayCss from 'themes/linkki/components/vaadin-dialog-overlay.css?inline';
import vaadinDatePickerCss from 'themes/linkki/components/vaadin-date-picker.css?inline';
import vaadinComboBoxCss from 'themes/linkki/components/vaadin-combo-box.css?inline';
import vaadinCheckboxCss from 'themes/linkki/components/vaadin-checkbox.css?inline';
import vaadinButtonCss from 'themes/linkki/components/vaadin-button.css?inline';
import vaadinAccordionPanelCss from 'themes/linkki/components/vaadin-accordion-panel.css?inline';
import inputFieldValidationCss from 'themes/linkki/components/input-field-validation.css?inline';
import inputFieldHoverCss from 'themes/linkki/components/input-field-hover.css?inline';
import inputFieldFocusRingCss from 'themes/linkki/components/input-field-focus-ring.css?inline';
import errorMessageCss from 'themes/linkki/components/error-message.css?inline';


if (!document['_vaadintheme_linkki_componentCss']) {
  registerStyles(
        'vaadin-time-picker',
        unsafeCSS(vaadinTimePickerCss.toString())
      );
      registerStyles(
        'vaadin-text-field',
        unsafeCSS(vaadinTextFieldCss.toString())
      );
      registerStyles(
        'vaadin-text-area',
        unsafeCSS(vaadinTextAreaCss.toString())
      );
      registerStyles(
        'vaadin-radio-button',
        unsafeCSS(vaadinRadioButtonCss.toString())
      );
      registerStyles(
        'vaadin-password-field',
        unsafeCSS(vaadinPasswordFieldCss.toString())
      );
      registerStyles(
        'vaadin-notification-card',
        unsafeCSS(vaadinNotificationCardCss.toString())
      );
      registerStyles(
        'vaadin-multi-select-combo-box',
        unsafeCSS(vaadinMultiSelectComboBoxCss.toString())
      );
      registerStyles(
        'vaadin-input-container',
        unsafeCSS(vaadinInputContainerCss.toString())
      );
      registerStyles(
        'vaadin-grid',
        unsafeCSS(vaadinGridCss.toString())
      );
      registerStyles(
        'vaadin-form-layout',
        unsafeCSS(vaadinFormLayoutCss.toString())
      );
      registerStyles(
        'vaadin-form-item',
        unsafeCSS(vaadinFormItemCss.toString())
      );
      registerStyles(
        'vaadin-dialog-overlay',
        unsafeCSS(vaadinDialogOverlayCss.toString())
      );
      registerStyles(
        'vaadin-date-picker',
        unsafeCSS(vaadinDatePickerCss.toString())
      );
      registerStyles(
        'vaadin-combo-box',
        unsafeCSS(vaadinComboBoxCss.toString())
      );
      registerStyles(
        'vaadin-checkbox',
        unsafeCSS(vaadinCheckboxCss.toString())
      );
      registerStyles(
        'vaadin-button',
        unsafeCSS(vaadinButtonCss.toString())
      );
      registerStyles(
        'vaadin-accordion-panel',
        unsafeCSS(vaadinAccordionPanelCss.toString())
      );
      registerStyles(
        'input-field-validation',
        unsafeCSS(inputFieldValidationCss.toString())
      );
      registerStyles(
        'input-field-hover',
        unsafeCSS(inputFieldHoverCss.toString())
      );
      registerStyles(
        'input-field-focus-ring',
        unsafeCSS(inputFieldFocusRingCss.toString())
      );
      registerStyles(
        'error-message',
        unsafeCSS(errorMessageCss.toString())
      );
      
  document['_vaadintheme_linkki_componentCss'] = true;
}

if (import.meta.hot) {
  import.meta.hot.accept((module) => {
    window.location.reload();
  });
}

