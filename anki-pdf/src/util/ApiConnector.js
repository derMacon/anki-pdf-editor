export const ApiConnector = {
  submitCard(cardObj) {
    console.log(`mock-up:\n
      front${cardObj.front}\n
      back${cardObj.back}\n
      tags${cardObj.tags}`);
  }
}
