export const AppHelper = {
  /**
   * Safely parse a json string
   *
   * @param text the json string
   * @returns the parsed json object if parseable, otherwise, the text itself
   */
  tryParseJson(text: string): string | object {
    try {
      return JSON.parse(text);
    } catch (err) {
      return text;
    }
  },
};
