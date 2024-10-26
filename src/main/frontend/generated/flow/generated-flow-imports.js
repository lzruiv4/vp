import { injectGlobalCss } from 'Frontend/generated/jar-resources/theme-util.js';

import { css, unsafeCSS, registerStyles } from '@vaadin/vaadin-themable-mixin';
import $cssFromFile_0 from 'Frontend/generated/jar-resources/styles/linkki-application-layout.css?inline';

injectGlobalCss($cssFromFile_0.toString(), 'CSSImport end', document);
import $cssFromFile_1 from 'Frontend/generated/jar-resources/styles/linkki-vaadin-field.css?inline';
const $css_1 = typeof $cssFromFile_1  === 'string' ? unsafeCSS($cssFromFile_1) : $cssFromFile_1;
registerStyles('vaadin-*', $css_1, {moduleId: 'flow_css_mod_1'});
import $cssFromFile_2 from 'Frontend/generated/jar-resources/styles/linkki-section.css?inline';

injectGlobalCss($cssFromFile_2.toString(), 'CSSImport end', document);
import $cssFromFile_3 from 'Frontend/generated/jar-resources/styles/linkki-dialog.css?inline';

injectGlobalCss($cssFromFile_3.toString(), 'CSSImport end', document);
import $cssFromFile_4 from 'Frontend/generated/jar-resources/styles/linkki-page.css?inline';

injectGlobalCss($cssFromFile_4.toString(), 'CSSImport end', document);
import $cssFromFile_5 from 'Frontend/generated/jar-resources/styles/linkki-has-icon.css?inline';

injectGlobalCss($cssFromFile_5.toString(), 'CSSImport end', document);
import $cssFromFile_6 from 'Frontend/generated/jar-resources/styles/linkki-headline.css?inline';

injectGlobalCss($cssFromFile_6.toString(), 'CSSImport end', document);
import $cssFromFile_7 from 'Frontend/generated/jar-resources/styles/linkki-messages.css?inline';

injectGlobalCss($cssFromFile_7.toString(), 'CSSImport end', document);
import $cssFromFile_8 from 'Frontend/generated/jar-resources/styles/error-page.css?inline';

injectGlobalCss($cssFromFile_8.toString(), 'CSSImport end', document);
import '@vaadin/tooltip/src/vaadin-tooltip.js';
import 'Frontend/generated/jar-resources/src/linkki-text.ts';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/icon/src/vaadin-icon.js';
import '@vaadin/icons/vaadin-iconset.js';
import '@vaadin/button/src/vaadin-button.js';
import 'Frontend/generated/jar-resources/buttonFunctions.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/vaadin-lumo-styles/color-global.js';
import '@vaadin/vaadin-lumo-styles/typography-global.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === '0ed05da57d1e5ae5f074b3750c74b1f878adfc22b7b75e2f931c9b6a2c6c7492') {
    pending.push(import('./chunks/chunk-4ca77b19d384ed81790452fab2e5b8a91dd09aedd86c77fd6a1d5c8d62066f7f.js'));
  }
  if (key === 'c568eac31218fcf5ac6415e26d86c3341769dfae606751b211ea0b642122b7a5') {
    pending.push(import('./chunks/chunk-4ca77b19d384ed81790452fab2e5b8a91dd09aedd86c77fd6a1d5c8d62066f7f.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;
window.Vaadin.Flow.resetFocus = () => {
 let ae=document.activeElement;
 while(ae&&ae.shadowRoot) ae = ae.shadowRoot.activeElement;
 return !ae || ae.blur() || ae.focus() || true;
}