package com.hsj86715.ingress.glyph;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by hushujun on 2017/5/16.
 */

public class BaseDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "BaseDecoration";

    protected boolean isFirstColumn(@NonNull RecyclerView.LayoutManager layoutManager, int position) {
        final int spanCount;
        final int orientation;
        final int itemCount = layoutManager.getItemCount();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            orientation = ((GridLayoutManager) layoutManager).getOrientation();
            if (orientation == GridLayoutManager.VERTICAL) {
                return position % spanCount == 0;
            } else {
                final int more = itemCount % spanCount;
                if (more == 0) {
                    return position % spanCount == 0;
                } else {
                    final int row = position / spanCount;
                    if (row < more) {
                        return position % spanCount == 0;
                    } else {
                        return (position + row - more) % spanCount == 0;
                    }
                }
            }
        } else if (layoutManager instanceof LinearLayoutManager) {
            orientation = ((LinearLayoutManager) layoutManager).getOrientation();
            if (orientation == LinearLayoutManager.VERTICAL) {
                return true;
            } else {
                return position == 0;
            }
        } else {
            Log.i(TAG, "isFirstColumn==>the LayoutManager is not support," + layoutManager);
        }
        return false;
    }

    protected boolean isLastColumn(@NonNull RecyclerView.LayoutManager layoutManager, int position) {
        final int spanCount;
        final int orientation;
        final int itemCount = layoutManager.getItemCount();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            orientation = ((GridLayoutManager) layoutManager).getOrientation();
            if (orientation == GridLayoutManager.VERTICAL) {
                return position % spanCount == (spanCount - 1) || position == itemCount - 1;
            } else {
                final int more = itemCount % spanCount;
                if (more == 0) {
                    return position % spanCount == spanCount - 1;
                } else {
                    final int row = position / spanCount;
                    if (row < more) {
                        return position % spanCount == spanCount - 1;
                    } else {
                        return (position + row - more) % spanCount == spanCount - 1;
                    }
                }
            }
        } else if (layoutManager instanceof LinearLayoutManager) {
            orientation = ((LinearLayoutManager) layoutManager).getOrientation();
            if (orientation == LinearLayoutManager.VERTICAL) {
                return true;
            } else {
                return position == itemCount - 1;
            }
        } else {
            Log.i(TAG, "isLastColumn==>the LayoutManager is not support," + layoutManager);
        }
        return false;
    }

    protected boolean isFirstRow(@NonNull RecyclerView.LayoutManager layoutManager, int position) {
        final int spanCount;
        final int orientation;
        final int itemCount = layoutManager.getItemCount();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            orientation = ((GridLayoutManager) layoutManager).getOrientation();
            if (orientation == GridLayoutManager.VERTICAL) {
                return position < spanCount;
            } else {
                final int more = itemCount % spanCount;
                if (more == 0) {
                    return position < itemCount / spanCount;
                } else {
                    return position < itemCount / spanCount + 1;
                }
            }
        } else if (layoutManager instanceof LinearLayoutManager) {
            orientation = ((LinearLayoutManager) layoutManager).getOrientation();
            if (orientation == LinearLayoutManager.VERTICAL) {
                return position == 0;
            } else {
                return true;
            }
        } else {
            Log.i(TAG, "isFirstRow==>the LayoutManager is not support," + layoutManager);
        }
        return false;
    }

    protected boolean isLastRow(@NonNull RecyclerView.LayoutManager layoutManager, int position) {
        final int spanCount;
        final int orientation;
        final int itemCount = layoutManager.getItemCount();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            orientation = ((GridLayoutManager) layoutManager).getOrientation();
            if (orientation == GridLayoutManager.VERTICAL) {
                return position >= itemCount - spanCount;
            } else {
                int more = itemCount % spanCount;
                int row = position / spanCount + 1;
                return row == spanCount || spanCount * more < position;
            }
        } else if (layoutManager instanceof LinearLayoutManager) {
            orientation = ((LinearLayoutManager) layoutManager).getOrientation();
            if (orientation == LinearLayoutManager.VERTICAL) {
                return position == itemCount - 1;
            } else {
                return true;
            }
        } else {
            Log.i(TAG, "isLastRow==>the LayoutManager is not support," + layoutManager);
        }
        return false;
    }
}
