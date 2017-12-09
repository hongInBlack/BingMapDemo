package mobi.chy.bingsdk.backend;


import mobi.chy.bingsdk.backend.base.BaseCallback;

/**
 * Created by Leonid Veremchuk on 11/9/16.
 */

public interface IInfoboxActionCallback extends BaseCallback {
    void onLabelClick(String labelId);
}
