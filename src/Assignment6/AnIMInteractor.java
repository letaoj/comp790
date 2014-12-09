package Assignment6;

import trace.echo.ListEditDisplayed;
import trace.echo.ListEditInput;
import trace.echo.modular.ListEditObserved;
import trace.echo.modular.OperationName;
import util.annotations.Tags;
import util.session.Communicator;
import util.tags.ApplicationTags;
import util.tags.InteractiveTags;
import echo.modular.ListObserver;
import echo.modular.SimpleList;

@Tags({ApplicationTags.IM, InteractiveTags.INTERACTOR})
public class AnIMInteractor implements ListObserver {

  protected SimpleList history;
  protected Communicator communicator;
  protected UI ui;
  protected boolean callback = true;

  public AnIMInteractor(SimpleList history, Communicator communicator) {
    this.history = history;
    this.communicator = communicator;
  }

  protected void addToList(int index, String newValue) {
    ((ReplicatedSimpleList) history).replicatedAdd(index, newValue);
  }

  protected void processHistory(String input) {
    ListEditInput.newCase(OperationName.ADD, history.size(), input, ApplicationTags.IM, this);
    addToList(history.size(), "[" + communicator.getClientName() + "]" + input);
  }

  @Override
  public void elementAdded(int anIndex, Object aNewValue) {
    ListEditObserved.newCase(OperationName.ADD, anIndex, aNewValue, ApplicationTags.IM, this);
    displayOutput((String) history.get(anIndex));
    ListEditDisplayed.newCase(OperationName.ADD, anIndex, aNewValue, ApplicationTags.IM, this);

  }

  public void setUI(UI ui) {
    this.ui = ui;
  }

  protected void displayOutput(String newValue) {
    ui.changeHistory(history);
  }

  @Override
  public void elementRemoved(int anIndex, Object aNewValue) {
    // TODO Auto-generated method stub

  }

}
