package com.hsj86715.ingress.glyph.pages;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.hsj86715.ingress.glyph.R;
import com.hsj86715.ingress.glyphres.data.Category;
import com.hsj86715.ingress.glyphres.data.EditTemp;
import com.hsj86715.ingress.glyphres.data.GlyphInfo;
import com.hsj86715.ingress.glyphres.data.GlyphModel;
import com.hsj86715.ingress.glyphres.data.Name;

import java.util.List;

import cn.com.farmcode.utility.tools.Logger;

/**
 * @author hushujun
 */
public class EditFragment extends Fragment {
    private TextView mNameTx;
    private EditText mAlisaEt, mAlisa1Et, mAlisa2Et, mAlisa3Et;
    private Spinner mCatSpinner;
    private GlyphInfo mCurrentGlyph;
    private Name mCurrentName;
    private EditTemp mEditTemp;

    private long mSelectCatId;
    private boolean mCatChanged = false;
    private boolean mNameChanged = false;

    private UpdateNameTask mUpdateNameTask;


    public static EditFragment getInstance(GlyphInfo glyphInfo) {
        EditFragment fragment = new EditFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("glyph", glyphInfo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mNameTx = view.findViewById(R.id.edit_name_text);
        mAlisaEt = view.findViewById(R.id.edit_alisa_et);
        mAlisa1Et = view.findViewById(R.id.edit_alisa1_et);
        mAlisa2Et = view.findViewById(R.id.edit_alisa2_et);
        mAlisa3Et = view.findViewById(R.id.edit_alisa3_et);
        mCatSpinner = view.findViewById(R.id.edit_cat_sp);

        mAlisaEt.addTextChangedListener(new InnerTextWatcher(0));
        mAlisa1Et.addTextChangedListener(new InnerTextWatcher(1));
        mAlisa2Et.addTextChangedListener(new InnerTextWatcher(2));
        mAlisa3Et.addTextChangedListener(new InnerTextWatcher(3));


        mCurrentGlyph = getArguments().getParcelable("glyph");
        if (mCurrentGlyph != null) {
            mNameTx.setText(mCurrentGlyph.getName());
            new CatTask().execute();
            new EditTempTask().execute(mCurrentGlyph.getId());
        }
    }

    private void updateAlias(Name name) {
        mCurrentName = name;
        if (name == null) {
            return;
        }
        if (mEditTemp == null) {
            mEditTemp = new EditTemp();
        }
        mEditTemp.copyFromName(name);
        mAlisaEt.setText(name.getAlias());
        mAlisa1Et.setText(name.getAlias1());
        mAlisa2Et.setText(name.getAlias2());
        mAlisa3Et.setText(name.getAlias3());
    }

    private void updateWithEditTemp(EditTemp editTemp) {
        mEditTemp = editTemp;
        if (editTemp != null) {
            mAlisaEt.setText(editTemp.getAlias());
            mAlisa1Et.setText(editTemp.getAlias1());
            mAlisa2Et.setText(editTemp.getAlias2());
            mAlisa3Et.setText(editTemp.getAlias3());
            SpinnerAdapter adapter = mCatSpinner.getAdapter();
            if (adapter != null) {
                for (int i = 0; i < adapter.getCount(); i++) {
                    Category category = (Category) adapter.getItem(i);
                    if (TextUtils.equals(editTemp.getCatName(), category.getName())) {
                        mCatSpinner.setSelection(i);
                        break;
                    }
                }
            }
        } else {
            new NameTask().execute(mCurrentGlyph.getNameId());
        }
    }

    private void updateCatSpinner(final List<Category> categories) {
        mCatSpinner.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, categories));
        int index = 0;
        for (; index < categories.size(); index++) {
            if (TextUtils.equals(categories.get(index).getName(), mCurrentGlyph.getCategory())) {
                mSelectCatId = categories.get(index).getId();
                break;
            }
        }
        mCatSpinner.setSelection(index);
        mCatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category category = categories.get(position);
                if (mSelectCatId != category.getId()) {
                    mSelectCatId = category.getId();
                    mCurrentGlyph.setCategory(category.getName());
                    mCatChanged = true;
                }

                if (mEditTemp == null) {
                    mEditTemp = new EditTemp();
                    mEditTemp.setGlyphId(mCurrentGlyph.getId());
                    mEditTemp.setCatName(((Category) mCatSpinner.getSelectedItem()).getName());
                }
                mEditTemp.setCatName(category.getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void save() {
        hideIMME();
        if (!mCatChanged && !mNameChanged) {
            Snackbar.make(getView(), R.string.toast_edit_save_no_change, Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (mCatChanged) {
            new UpdateCatTask().execute(mCurrentGlyph.getId(), mSelectCatId);
        }
        if (mNameChanged) {
            if (mUpdateNameTask != null) {
                return;
            } else {
                mUpdateNameTask = new UpdateNameTask();
                mUpdateNameTask.execute(String.valueOf(mCurrentGlyph.getNameId()), mAlisaEt.getText().toString(),
                        mAlisa1Et.getText().toString(), mAlisa2Et.getText().toString(), mAlisa3Et.getText().toString());
            }
        }
    }

    private void hideIMME() {
        try {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveEditTemp() {
        if (mCatChanged || mNameChanged) {
            new InsertOrUpdateEditTempTask().execute(mEditTemp);
        }
    }

    public boolean isSaved() {
        return !mCatChanged && !mNameChanged;
    }

    private void handleSaveResult(int result) {
        if (result > 0) {
            if (mUpdateNameTask == null || mUpdateNameTask.getStatus() == AsyncTask.Status.FINISHED) {
                new DeleteEditTempTask().execute(mCurrentGlyph.getId());

                if (getActivity() != null && !getActivity().isFinishing()) {
                    if (getView() != null) {
                        Snackbar.make(getView(), R.string.toast_edit_save_success, Snackbar.LENGTH_SHORT).show();
                    }
                }
            }
        } else {
            if (getActivity() != null && !getActivity().isFinishing()) {
                if (getView() != null) {
                    Snackbar.make(getView(), R.string.toast_edit_save_fail, Snackbar.LENGTH_SHORT).show();
                }
            }
        }
    }

    private class InnerTextWatcher implements TextWatcher {
        private int index;

        public InnerTextWatcher(int idx) {
            this.index = idx;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean isEmpty = TextUtils.isEmpty(s);
            if (mEditTemp == null) {
                mEditTemp = new EditTemp();
                mEditTemp.setGlyphId(mCurrentGlyph.getId());

                if (mCurrentName != null) {
                    String nameAlisa = getAliasByIdx();
                    boolean isNameAlisaEmpty = TextUtils.isEmpty(nameAlisa);
                    if (!isEmpty && !isNameAlisaEmpty && !TextUtils.equals(s, nameAlisa)) {
                        mNameChanged = true;
                    } else if (isEmpty != isNameAlisaEmpty) {
                        mNameChanged = true;
                    }
                }
            } else {
                String nameAlisa = getEditAliasByIdx();
                boolean isNameAlisaEmpty = TextUtils.isEmpty(nameAlisa);
                if (!isEmpty && !isNameAlisaEmpty && !TextUtils.equals(s, nameAlisa)) {
                    mNameChanged = true;
                } else if (isEmpty != isNameAlisaEmpty) {
                    mNameChanged = true;
                }
            }
            setEditAliasByIdx(s.toString());
        }

        private String getAliasByIdx() {
            switch (index) {
                case 0:
                    return mCurrentName.getAlias();
                case 1:
                    return mCurrentName.getAlias1();
                case 2:
                    return mCurrentName.getAlias2();
                case 3:
                    return mCurrentName.getAlias3();
                default:
                    return null;
            }
        }

        private String getEditAliasByIdx() {
            switch (index) {
                case 0:
                    return mEditTemp.getAlias();
                case 1:
                    return mEditTemp.getAlias1();
                case 2:
                    return mEditTemp.getAlias2();
                case 3:
                    return mEditTemp.getAlias3();
                default:
                    return null;
            }
        }

        private void setEditAliasByIdx(String alias) {
            switch (index) {
                case 0:
                    mEditTemp.setAlias(alias);
                    break;
                case 1:
                    mEditTemp.setAlias1(alias);
                    break;
                case 2:
                    mEditTemp.setAlias2(alias);
                    break;
                case 3:
                    mEditTemp.setAlias3(alias);
                    break;
                default:
                    break;
            }
        }
    }

    private class CatTask extends AsyncTask<Void, Void, List<Category>> {
        @Override
        protected List<Category> doInBackground(Void... voids) {
            return GlyphModel.getInstance(getActivity()).getCategories();
        }

        @Override
        protected void onPostExecute(List<Category> categories) {
            updateCatSpinner(categories);
        }
    }

    private class EditTempTask extends AsyncTask<Long, Void, EditTemp> {
        @Override
        protected EditTemp doInBackground(Long... longs) {
            return GlyphModel.getInstance(getActivity()).getEditTemp(longs[0]);
        }

        @Override
        protected void onPostExecute(EditTemp editTemp) {
            updateWithEditTemp(editTemp);
        }
    }

    private class NameTask extends AsyncTask<Long, Void, Name> {

        @Override
        protected Name doInBackground(Long... longs) {
            return GlyphModel.getInstance(getActivity()).getGlyphNames(longs[0]);
        }

        @Override
        protected void onPostExecute(Name name) {
            updateAlias(name);
        }
    }

    private class InsertOrUpdateEditTempTask extends AsyncTask<EditTemp, Void, Long> {
        @Override
        protected Long doInBackground(EditTemp... editTemps) {
            return GlyphModel.getInstance(getActivity()).insertOrUpdateEditTemp(editTemps[0]);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            Logger.e("Inset or update EditTemp " + aLong);
        }
    }

    private class UpdateCatTask extends AsyncTask<Long, Void, Integer> {
        @Override
        protected Integer doInBackground(Long... longs) {
            return GlyphModel.getInstance(getActivity()).updateGlyphCategory(longs[0], longs[1]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            handleSaveResult(integer);
            Logger.e("Update category " + integer);
        }
    }

    private class UpdateNameTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... strings) {
            return GlyphModel.getInstance(getActivity()).updateGlyphName(strings[0], strings[1],
                    strings[2], strings[3], strings[4]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            handleSaveResult(integer);
            Logger.e("Update name " + integer);
        }
    }

    private class DeleteEditTempTask extends AsyncTask<Long, Void, Integer> {
        @Override
        protected Integer doInBackground(Long... longs) {
            return GlyphModel.getInstance(getActivity()).deleteEditTemp(longs[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            Logger.e("Delete EditTemp " + integer);
        }
    }
}
