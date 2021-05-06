import { BootPage } from './app.po';

describe('boot App', () => {
  let page: BootPage;

  beforeEach(() => {
    page = new BootPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
