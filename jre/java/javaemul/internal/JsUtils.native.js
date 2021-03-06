/**
 * @param {*} value
 * @return {boolean}
 */
javaemul_internal_JsUtils.m_isUndefined__java_lang_Object = function(value) {
  javaemul_internal_JsUtils.$clinit();
  return value === undefined;
};

/**
 * @param {*} value
 * @return {number}
 */
javaemul_internal_JsUtils.m_unsafeCastToDouble__java_lang_Object = function(
    value) {
  javaemul_internal_JsUtils.$clinit();
  return /** @type {number} */ (value);
};

/**
 * @param {*} value
 * @return {boolean}
 */
javaemul_internal_JsUtils.m_unsafeCastToBoolean__java_lang_Object = function(
    value) {
  javaemul_internal_JsUtils.$clinit();
  return /** @type {boolean} */ (value);
};

/**
 * @param {*} value
 * @return {*}
 */
javaemul_internal_JsUtils.m_uncheckedCast__java_lang_Object = function(value) {
  javaemul_internal_JsUtils.$clinit();
  return value;
};

/**
 * @param {*} map
 * @param {string} key
 * @return {*}
 */
javaemul_internal_JsUtils.m_getProperty__java_lang_Object__java_lang_String =
    function(map, key) {
  javaemul_internal_JsUtils.$clinit();
  return map[key];
};

/**
 * @param {*} map
 * @param {string} key
 * @param {*} value
 * @return {*}
 */
javaemul_internal_JsUtils
    .m_setProperty__java_lang_Object__java_lang_String__java_lang_Object =
    function(map, key, value) {
  javaemul_internal_JsUtils.$clinit();
  map[key] = value;
};

/**
 * @param {*} map
 * @param {string} key
 * @param {*} value
 * @return {*}
 */
javaemul_internal_JsUtils
    .m_setPropertySafe__java_lang_Object__java_lang_String__java_lang_Object =
    function(map, key, value) {
  javaemul_internal_JsUtils.$clinit();
  try {
    // This may throw exception in strict mode.
    map[key] = value;
  } catch (ignored) {
  }
};
