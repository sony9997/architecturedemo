package org.hed.archdemo.ui.user;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hed.archdemo.R;
import org.hed.archdemo.databinding.ListFragmentBinding;
import org.hed.archdemo.databinding.UsersItemBinding;
import org.hed.archdemo.di.Injectable;
import org.hed.archdemo.model.User;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by hedong on 2017/11/30.
 */

public class UsersFragment extends LifecycleFragment implements Injectable {
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ListFragmentBinding mBinding;
    private UsersAdapter usersAdapter=new UsersAdapter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false);
        mBinding.productsList.setAdapter(usersAdapter);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final UserViewModel viewModel= ViewModelProviders.of(this, viewModelFactory).get(UserViewModel.class);
        viewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                Log.d("users","onChanged:"+users);
                if (users != null) {
                    mBinding.productsList.setVisibility(View.VISIBLE);
                    mBinding.loadingTv.setVisibility(View.GONE);
                } else {
                    mBinding.productsList.setVisibility(View.GONE);
                    mBinding.loadingTv.setVisibility(View.VISIBLE);
                }
                usersAdapter.setUsers(users);
                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                mBinding.executePendingBindings();
            }
        });
    }

    class UsersAdapter extends RecyclerView.Adapter<UserHolder>{
        private List<User> list=null;
        UsersAdapter(){
        }

        public void setUsers(List<User> users){
            list=users;
            notifyDataSetChanged();
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            UsersItemBinding usersItemBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.users_item, parent, false);
            return new UserHolder(usersItemBinding);
        }

        @Override
        public void onBindViewHolder(UserHolder holder, int position) {
            User user=list.get(position);
            holder.binding.setUser(user);
            holder.binding.executePendingBindings();
        }

        @Override
        public int getItemCount() {
            return list!=null?list.size():0;
        }
    }

    class UserHolder extends RecyclerView.ViewHolder{
        UsersItemBinding binding;

        public UserHolder(UsersItemBinding usersItemBinding) {
            super(usersItemBinding.getRoot());
            binding=usersItemBinding;
        }
    }
}
