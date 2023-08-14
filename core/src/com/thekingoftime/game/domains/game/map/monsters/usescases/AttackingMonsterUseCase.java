package com.thekingoftime.game.domains.game.map.monsters.usescases;

public class AttackingMonsterUseCase {

    public boolean isAttacking;
    public float attackTime;

    public void execute(MovimentUseCase movimentUseCase, float delta, float distanceToPlayer) {
        if (distanceToPlayer <= movimentUseCase.monsterActor.monsterEntity.attackDistance) {
            isAttacking = true;
            attackTime += delta;

            movimentUseCase.logicRetreat = false;

            if (attackTime > movimentUseCase.monsterActor.monsterEntity.attackTime) { // 2 segundos entre ataques
                System.out.println("Atacou!");
                attackTime = 0f;
                movimentUseCase.logicRetreat = true;
            }
            // lógica para recuar
        } else {
            movimentUseCase.logicRetreat = false;
            isAttacking = false;
            // lógica de movimento normal
        }
    }
}
