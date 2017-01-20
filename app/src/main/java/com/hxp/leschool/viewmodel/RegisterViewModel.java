package com.hxp.leschool.viewmodel;

import com.hxp.leschool.databinding.RegisterFmBinding;
import com.hxp.leschool.view.fragment.RegisterFragment;

/**
 * Created by hxp on 17-1-20.
 */

public class RegisterViewModel {

    private RegisterFragment mRegisterFragment;
    private RegisterFmBinding mRegisterFmBinding;

    public RegisterViewModel(RegisterFragment registerFragment, RegisterFmBinding registerFmBinding) {

        mRegisterFragment = registerFragment;
        mRegisterFmBinding = registerFmBinding;

        mRegisterFmBinding.setMRegisterViewModel(this);
    }
}
