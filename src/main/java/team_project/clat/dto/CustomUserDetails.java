package team_project.clat.dto;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import team_project.clat.domain.Member;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    public CustomUserDetails() {
        this.member = null;  // 실제로는 사용되지 않겠지만, 기본 생성자 필요할 때 사용
    }


    private final Member member;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return member.getUserType().getDescription();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    public Member getMember() {
        return member;
    }




}
